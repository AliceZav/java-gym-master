package ru.yandex.practicum.gym;

import java.util.*;

public class Timetable {

    private HashMap<DayOfWeek, TreeMap<TimeOfDay, ArrayList<TrainingSession>>> timetable = new HashMap<>();

    public void addNewTrainingSession(TrainingSession trainingSession) {
        DayOfWeek dayOfWeek = trainingSession.getDayOfWeek();
        TimeOfDay timeOfDay = trainingSession.getTimeOfDay();

        if (!timetable.containsKey(dayOfWeek)) {
            timetable.put(dayOfWeek, new TreeMap<>());
        }

        TreeMap<TimeOfDay, ArrayList<TrainingSession>> dayTimetable = timetable.get(dayOfWeek);
        if (!timetable.get(dayOfWeek).containsKey(timeOfDay)) {
            timetable.get(dayOfWeek).put(timeOfDay, new ArrayList<>());
        }

        dayTimetable.get(timeOfDay).add(trainingSession);
    }

    public TreeMap<TimeOfDay, ArrayList<TrainingSession>> getTrainingSessionsForDay(DayOfWeek dayOfWeek) {
        if (!timetable.containsKey(dayOfWeek)) {
            return new TreeMap<>();
        } else {
            return timetable.get(dayOfWeek);//как реализовать, тоже непонятно, но сложность должна быть О(1)
        }
        }

    public ArrayList<TrainingSession> getTrainingSessionsForDayAndTime(DayOfWeek dayOfWeek, TimeOfDay timeOfDay) {
        if (!timetable.containsKey(dayOfWeek)) {
            return new ArrayList<>(); //как реализовать, тоже непонятно, но сложность должна быть О(1)
        } else {
            if (!timetable.get(dayOfWeek).containsKey(timeOfDay)) {
                return new ArrayList<>();
            } else {
                return timetable.get(dayOfWeek).get(timeOfDay);
            }
        }
    }

    public Map<Coach, Integer> getCountByCoaches() {
        Map<Coach, Integer> countByCoach = new HashMap<>();

        for (TreeMap<TimeOfDay, ArrayList<TrainingSession>> dayTimetable : timetable.values()) {
            for (ArrayList<TrainingSession> trainingList : dayTimetable.values()) {
                for (TrainingSession trainingSession : trainingList) {
                    Coach coach = trainingSession.getCoach();
                    countByCoach.put(coach, countByCoach.getOrDefault(coach, 0) + 1);
                }
            }
        }

        List<Map.Entry<Coach, Integer>> sortedEntries = new ArrayList<>(countByCoach.entrySet());
        sortedEntries.sort((entry1, entry2) ->
                                entry2.getValue().compareTo(entry1.getValue()));

        Map<Coach, Integer> sortedCoachCount = new LinkedHashMap<>();
        for (Map.Entry<Coach, Integer> entry : sortedEntries) {
            sortedCoachCount.put(entry.getKey(), entry.getValue());
        }

        return sortedCoachCount;
    }
}
