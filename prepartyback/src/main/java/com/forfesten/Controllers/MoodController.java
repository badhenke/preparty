package com.forfesten.Controllers;

import com.forfesten.DaoWrappers.MoodDAOWrapper;
import com.forfesten.Models.Mood;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller for Moods
 */
@RestController
@RequestMapping(value = "/api/moods")
public class MoodController {

    @Autowired
    MoodDAOWrapper moodDAOWrapper;

    /**
     * Get all Mood from db
     * @return List of Mood
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity getAll() {
        List<Mood> moodList = moodDAOWrapper.getAll();
        return new ResponseEntity(moodList, HttpStatus.OK);
    }

}
