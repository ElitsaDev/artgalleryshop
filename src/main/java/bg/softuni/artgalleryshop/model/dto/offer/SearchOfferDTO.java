package bg.softuni.artgalleryshop.model.dto.offer;

import javax.validation.constraints.NotEmpty;
import java.util.UUID;

public class SearchOfferDTO {

    private UUID id;
    @NotEmpty
    private String query;
    public SearchOfferDTO() {
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
