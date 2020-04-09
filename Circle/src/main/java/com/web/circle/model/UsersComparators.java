package com.web.circle.model;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import com.web.circle.model.tablepaging.Direction;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

public final class UsersComparators {
	
	@EqualsAndHashCode
	@AllArgsConstructor
	@Getter
    static class Key {
        String name;
        Direction dir;
    }

    static Map<Key, Comparator<UsersModel>> map = new HashMap<>();

    static {
        map.put(new Key("userId", Direction.desc), Comparator.comparing(UsersModel::getUserId));
        map.put(new Key("userId", Direction.asc), Comparator.comparing(UsersModel::getUserId).reversed());
    }

    public static Comparator<UsersModel> getComparator(String name, Direction dir) {
        return map.get(new Key(name, dir));
    }

    private UsersComparators() {
    }
	
}
