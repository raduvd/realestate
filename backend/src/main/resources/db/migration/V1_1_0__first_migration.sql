CREATE TABLE ad(
  adId VARCHAR (20) NOT NULL ,
  squareMeters FLOAT NOT NULL,
  pageType VARCHAR (20) NOT NULL,
  currency VARCHAR (20) NOT NULL,
  PRIMARY KEY (adId),
  UNIQUE (adId, squareMeters,pageType));

CREATE TABLE adPrice(
  adId VARCHAR(20) NOT NULL ,
  addedAtDate DATE NOT NULL ,
  squareMeterPrice FLOAT NOT NULL,
  valid BOOLEAN NOT NULL,
  PRIMARY KEY (adId),
  UNIQUE (adId, addedAtDate));