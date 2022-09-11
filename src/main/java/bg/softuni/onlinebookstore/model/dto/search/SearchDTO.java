package bg.softuni.onlinebookstore.model.dto.search;

import javax.validation.constraints.NotEmpty;

public class SearchDTO {
    @NotEmpty
    private String searchText;

    public SearchDTO() {
    }

    public SearchDTO(String searchText) {
        this.searchText = searchText;
    }

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }
}
