package pfs.lms.enquiry.menustructure.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sajeev on 13-May-21.
 */
@Entity
@ToString
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer identifier;


    private String userRole;
    private String userRoleName;



    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL )
    @JoinColumn(name="menu__id",referencedColumnName = "identifier")
    private List<MenuHeader> menuHeaders;


    public void addMenuHeader(MenuHeader menuHeader) {
        if (this.getMenuHeaders() == null) {

            List<MenuHeader> menuHeaderList = new ArrayList<>();
            this.setMenuHeaders(menuHeaderList);
            this.getMenuHeaders().add(menuHeader);
        } else  {
            this.getMenuHeaders().add(menuHeader);
        }
    }
}
