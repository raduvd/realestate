import { combineReducers } from "redux";

const apartmentAverageReducer = (apartmentAverageList = [], action) => {
  if (action.type === "APARTMENT_AVERAGE_LIST") {
    return action.apartmentAverageList;
  } else {
    return apartmentAverageList;
  }
};

const fieldAverageReducer = (fieldAverageList = [], action) => {
  if (action.type === "FIELD_AVERAGE_LIST") {
    return action.fieldAverageList;
  } else {
    return fieldAverageList;
  }
};

const houseAverageReducer = (houseAverageList = [], action) => {
  if (action.type === "HOUSE_AVERAGE_LIST") {
    return action.houseAverageList;
  } else {
    return houseAverageList;
  }
};

const pageTypeReducer = (pageType = null, action) => {
  if (action.type === "PAGE_TYPE") {
    return action.pageType;
  } else {
    return pageType;
  }
};

const numberOfPagesInputReducer = (numberOfPages = 1, action) => {
  if (action.type === "NUMBER_OF_PAGES") {
    return action.numberOfPages;
  } else {
    return numberOfPages;
  }
};

export const reducers = combineReducers({
  apartmentAverageList: apartmentAverageReducer,
  houseAverageList: houseAverageReducer,
  fieldAverageList: fieldAverageReducer,
  pageType: pageTypeReducer,
  numberOfPages: numberOfPagesInputReducer,
});
