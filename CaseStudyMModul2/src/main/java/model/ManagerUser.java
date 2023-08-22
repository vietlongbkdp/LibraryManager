package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ManagerUser {
    private List<User> userList;

    @Override
    public String toString() {
        return "ManagerUser{" +
                "userList=" + userList +
                '}';
    }
}

