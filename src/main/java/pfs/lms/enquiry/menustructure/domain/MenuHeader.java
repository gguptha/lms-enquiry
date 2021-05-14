package pfs.lms.enquiry.menustructure.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sajeev on 14-May-21.
 */

@Entity
@ToString
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class MenuHeader {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer identifier;

    private Integer serialNumber;

    private String id;

    private String title;

    private String translate;

    private String type;

    private String icon;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL )
    @JoinColumn(name="menuHeader__id",referencedColumnName = "identifier")
    private List<MenuItem> menuItems;


    public void addMenuitem (MenuItem menuItem) {
        if (this.getMenuItems() == null) {
            List<MenuItem> menuItemList = new ArrayList<>();
            this.setMenuItems(menuItemList);
            this.getMenuItems().add(menuItem);
        } else {
            this.getMenuItems().add(menuItem);
        }
    }

}
