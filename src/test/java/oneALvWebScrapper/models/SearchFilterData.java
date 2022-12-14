package oneALvWebScrapper.models;

import lombok.Data;

@Data
public class SearchFilterData {
    String searchQuery;
    String firstType;
    String firstBrand;
    String secondBrand;
    String firstModel;
    String expandFilterMenu;
    String sortingFilterComboBox;
    String sortingFilterValue;
}
