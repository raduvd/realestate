const getApartmentAverageList = (apartmentAverageList) => {
  return {
    type: "APARTMENT_AVERAGE_LIST",
    apartmentAverageList: apartmentAverageList,
  };
};

const getFieldAverageList = (fieldAverageList) => {
  return {
    type: "FIELD_AVERAGE_LIST",
    fieldAverageList: fieldAverageList,
  };
};

const getHouseAverageList = (houseAverageList) => {
  return {
    type: "HOUSE_AVERAGE_LIST",
    houseAverageList: houseAverageList,
  };
};

const getPageType = (pageType) => {
  return {
    type: "PAGE_TYPE",
    pageType: pageType,
  };
};

const getNumberOfPages = (numberOfPages) => {
  return {
    type: "NUMBER_OF_PAGES",
    numberOfPages: numberOfPages,
  };
};

export {
  getApartmentAverageList,
  getPageType,
  getNumberOfPages,
  getHouseAverageList,
  getFieldAverageList,
};
