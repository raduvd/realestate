CREATE TABLE IF NOT EXISTS ad
(
    adId         VARCHAR(20) NOT NULL,
    squareMeters FLOAT       NOT NULL,
    pageType     VARCHAR(20) NOT NULL,
    currency     VARCHAR(20) NOT NULL,
    PRIMARY KEY (adId, squareMeters, pageType)
);

CREATE TABLE IF NOT EXISTS adPrice
(
    adId                                 VARCHAR(20) NOT NULL,
    squareMeters                         FLOAT       NOT NULL,
    pageType                             VARCHAR(20) NOT NULL,
    addedAtDate                          DATE        NOT NULL,
    state                                VARCHAR(20) NOT NULL,
    squareMeterPrice                     FLOAT       NOT NULL,
    fluctuationInPercentageSinceLastDate FLOAT,
    PRIMARY KEY (adId, squareMeters, pageType, addedAtDate)
);

CREATE TABLE IF NOT EXISTS numberOfAds
(
    pageType           VARCHAR(20) NOT NULL,
    addedAtDate        DATE        NOT NULL,
    numberOfAdsPerPage INTEGER     NOT NULL,
    numberOfPages      INTEGER     NOT NULL,
    PRIMARY KEY (pageType, addedAtDate)
);

