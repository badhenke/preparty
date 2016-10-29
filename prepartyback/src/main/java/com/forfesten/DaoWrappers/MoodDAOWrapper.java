package com.forfesten.DaoWrappers;

import com.forfesten.Dao.Mood.MoodDAOImpl;
import com.forfesten.Models.Mood;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Wrapper for all mood actions to DAO
 */
@Service
public class MoodDAOWrapper {

    @Autowired
    MoodDAOImpl moodDAO;

    /**
     * Get all moods
     * @return List of Mood
     */
    public List<Mood> getAll(){
        return moodDAO.getAll();
    }

}
