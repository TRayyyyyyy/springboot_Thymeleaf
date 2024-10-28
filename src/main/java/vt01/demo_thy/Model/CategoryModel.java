package vt01.demo_thy.Model;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CategoryModel {

    @Id
    private Long categoryId;

    @NotEmpty(message = "Khong duoc bo trong")
    private String name;

    private boolean IsEdit = false;

    public CategoryModel() {

    }
}
