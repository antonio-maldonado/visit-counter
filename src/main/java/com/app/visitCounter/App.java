package com.app.visitCounter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class App {

	static class UserStats{
		private Optional<Long> visitCount;

        UserStats(Long count) {
            this.visitCount = Optional.ofNullable(count);
        }

		
        Optional<Long> getVisitCount() {
			return visitCount;
		}
		
	}
	
	static Map<Long, Long> count(Map<String, UserStats>... visits) {
	    if (visits == null) return new HashMap<>();

	    return Arrays.stream(visits)
	        .filter(Objects::nonNull)
	        .flatMap(map -> map.entrySet().stream())
	        .filter(entry -> {
	            try {
	                Long.parseLong(entry.getKey());
	                return true;
	            } catch (NumberFormatException e) {
	                return false;
	            }
	        })
	        .filter(entry -> entry.getValue() != null && entry.getValue().getVisitCount().isPresent())
	        .collect(Collectors.toMap(
	            entry -> Long.parseLong(entry.getKey()),
	            entry -> entry.getValue().getVisitCount().get(),
	            Long::sum
	        ));
	}
	
    public static void main(String[] args) {
        Map<String, UserStats> service1 = new HashMap<>();
        service1.put("1", new UserStats(5L));
        service1.put("2", new UserStats(3L));
        service1.put("invalid", new UserStats(10L));

        Map<String, UserStats> service2 = new HashMap<>();
        service2.put("1", new UserStats(2L)); 
        service2.put("3", new UserStats(null)); 
        service2.put("4", null); 

        Map<Long, Long> result = count(service1, service2);

        System.out.println("User visit summary:");
        for (Map.Entry<Long, Long> entry : result.entrySet()) {
            System.out.println("User ID: " + entry.getKey() + ", Total Visits: " + entry.getValue());
        }
    }
}
