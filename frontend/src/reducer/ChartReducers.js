export const apartmentsReducer = (apartments = [], action) => {
  if (action.type === "APARTMENTS") {
    return action.apartments;
  } else {
    return apartments;
  }
};

export const fieldsReducer = (fields = [], action) => {
  if (action.type === "FIELDS") {
    return action.fields;
  } else {
    return fields;
  }
};
export const housesReducer = (houses = [], action) => {
  if (action.type === "HOUSES") {
    return action.houses;
  } else {
    return houses;
  }
};
export const apartmentFluctuationsReducer = (
  apartmentFluctuations = [],
  action
) => {
  if (action.type === "APARTMENT_FLUCTUATIONS") {
    return action.apartmentFluctuations;
  } else {
    return apartmentFluctuations;
  }
};

export const fieldFluctuationsReducer = (fieldFluctuations = [], action) => {
  if (action.type === "FIELD_FLUCTUATIONS") {
    return action.fieldFluctuations;
  } else {
    return fieldFluctuations;
  }
};
export const houseFluctuationsReducer = (houseFluctuations = [], action) => {
  if (action.type === "HOUSE_FLUCTUATIONS") {
    return action.houseFluctuations;
  } else {
    return houseFluctuations;
  }
};

export const customFluctuationAveragesReducer = (
  customFluctuationAverages = [],
  action
) => {
  if (action.type === "CUSTOM_FLUCTUATIONS") {
    return action.customFluctuationAverages;
  } else {
    return customFluctuationAverages;
  }
};

export const numberOfAdsFluctuationReducer = (
  numberOfAdsFluctuation = { apartments: [], houses: [], fields: [] },
  action
) => {
  if (action.type === "NUMBER_OF_ADS_FLUCTUATION") {
    return action.numberOfAdsFluctuation;
  } else {
    return numberOfAdsFluctuation;
  }
};
