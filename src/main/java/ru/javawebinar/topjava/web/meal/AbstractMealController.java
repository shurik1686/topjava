package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.time.LocalDate;
import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.checkNew;

@Controller
public abstract class AbstractMealController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    MealService service;

    public List<Meal> getAll() {
        int userId = SecurityUtil.authUserId();
        log.info("getAll for User {}", userId);
        return service.getAll(userId);
    }

    public Meal get(int id) {
        int userId = SecurityUtil.authUserId();
        log.info("get {} for meal {}", id, userId);
        return service.get(id, userId);
    }

    public Meal create(Meal meal) {
        int userId = SecurityUtil.authUserId();
        log.info("create {}", meal);
        checkNew(meal);
        return service.create(meal, userId);
    }

    public void delete(int id) {
        int userId = SecurityUtil.authUserId();
        log.info("delete {} for user {}", id, userId);
        service.delete(id, userId);
    }

    public void update(Meal meal) {
        int userId = SecurityUtil.authUserId();
        log.info("update meal {} for user {}", meal, userId);
        service.update(meal, userId);
    }

    public List<Meal> getBetweenInclusive(LocalDate dateStart, LocalDate dateEnd) {
        int userId = SecurityUtil.authUserId();
        log.info("getBetweenInclusive {} {}", dateStart, dateEnd);
        return service.getBetweenInclusive(dateStart, dateEnd, userId);
    }
}
