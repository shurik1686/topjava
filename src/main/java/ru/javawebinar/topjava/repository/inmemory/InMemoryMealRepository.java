package ru.javawebinar.topjava.repository.inmemory;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryMealRepository implements MealRepository {
    private final Map<Integer, List<Meal>> repository = new ConcurrentHashMap<>();
    private final AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.meals.forEach(meal -> save(meal, 1));
    }

    @Override
    public Meal save(Meal meal, int userId) {
        if (!repository.containsKey(userId))
            repository.put(1, new LinkedList<>());
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            repository.get(userId).add(meal);
            return meal;
        }
        // handle case: update, but not present in storage
        int index = repository.get(userId)
                .indexOf(repository.get(userId)
                        .stream()
                        .filter(meal1 -> meal.getId().equals(meal1.getId()))
                        .findFirst().get());
        return repository.get(userId).set(index, meal);
    }

    @Override
    public boolean delete(int id, int userId) {
        return repository.get(userId).removeIf(meal -> meal.getId() == id);
    }

    @Override
    public Meal get(int id, int userId) {
        if (getAll(userId).stream().anyMatch(meal -> meal.getId() == id))
            return getAll(userId).stream().filter(meal -> meal.getId() == id).findFirst().get();
        else return null;
    }

    @Override
    public List<Meal> getAll(int userId) {
        return repository.get(userId).stream()
                .sorted((o1, o2) -> o2.getDate().compareTo(o1.getDate()))
                .collect(Collectors.toList());
    }
}

