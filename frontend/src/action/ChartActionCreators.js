export const getApartments = (apartments) => {
  return { type: "APARTMENTS", apartments: apartments };
};
export const getFields = (fields) => {
  return { type: "FIELDS", fields: fields };
};
export const getHouses = (houses) => {
  return { type: "HOUSES", houses: houses };
};
export const getApartmentFluctuations = (apartmentFluctuations) => {
  return {
    type: "APARTMENT_FLUCTUATIONS",
    apartmentFluctuations: apartmentFluctuations,
  };
};
export const getFieldFluctuations = (fieldFluctuations) => {
  return { type: "FIELD_FLUCTUATIONS", fieldFluctuations: fieldFluctuations };
};
export const getHouseFluctuations = (houseFluctuations) => {
  return { type: "HOUSE_FLUCTUATIONS", houseFluctuations: houseFluctuations };
};

export const getCustomFluctuationAverages = (customFluctuationAverages) => {
  return {
    type: "CUSTOM_FLUCTUATIONS",
    customFluctuationAverages: customFluctuationAverages,
  };
};

export const getNumberOfAdsFluctuation = (numberOfAdsFluctuation) => {
  return {
    type: "NUMBER_OF_ADS_FLUCTUATION",
    numberOfAdsFluctuation: numberOfAdsFluctuation,
  };
};
