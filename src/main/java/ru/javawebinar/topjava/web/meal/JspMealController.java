package ru.javawebinar.topjava.web.meal;

import org.dom4j.rule.Mode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping(value = "/meals")
public class JspMealController extends AbstractMealController{

    @RequestMapping(method = RequestMethod.GET)
    public String mealList(Model model){
        model.addAttribute("mealList",super.getAll());
        return "meals";
    }

    @Override
    public List<Meal> getAll() {
        return super.getAll();
    }

    @Override
    public Meal get(int id) {
        return super.get(id);
    }

    @Override
    public Meal create(Meal meal) {
        return super.create(meal);
    }

    @Override
    public void delete(int id) {
        super.delete(id);
    }

    @Override
    public void update(Meal meal) {
        super.update(meal);
    }

    @Override
    public List<Meal> getBetweenInclusive(LocalDate dateStart, LocalDate dateEnd) {
        return super.getBetweenInclusive(dateStart, dateEnd);
    }
}
