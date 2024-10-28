package vt01.demo_thy.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Categories")
public class CategoryEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    @Column(name = "category_name", length = 200, nullable = false, columnDefinition = "nvarchar(200)")
    private String categoryName;

    @Column(name = "image", length = 500, columnDefinition = "nvarchar(1000)")
    private String icon;
}
