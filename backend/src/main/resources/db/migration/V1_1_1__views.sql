--SIMPLE AVERAGES
create view apartmentDateAndPrice
as select p.addedAtDate, p.squareMeterPrice
from adprice p left join ad a
ON a.adId = p.adId AND a.squareMeters = p.squareMeters AND a.pageType = p.pageType
where a.pageType='APARTAMENT' and p.state='VALID';

create view apartmentAverages as select avg(squareMeterPrice) as squareMeterPriceAverage, count(squareMeterPrice) numberOfAds, addedAtDate
from apartmentDateAndPrice group by (addedAtDate);


create view houseDateAndPrice
as select p.addedAtDate, p.squareMeterPrice
from adprice p left join ad a
ON a.adId = p.adId AND a.squareMeters = p.squareMeters AND a.pageType = p.pageType
where a.pageType='CASE' and p.state='VALID';

create view houseAverages as select avg(squareMeterPrice) as squareMeterPriceAverage, count(squareMeterPrice) numberOfAds,addedAtDate
from houseDateAndPrice group by (addedAtDate);


create view fieldDateAndPrice
as select p.addedAtDate, p.squareMeterPrice
from adprice p left join ad a
ON a.adId = p.adId AND a.squareMeters = p.squareMeters AND a.pageType = p.pageType
where a.pageType='TEREN' and p.state='VALID';

create view fieldAverages as select avg(squareMeterPrice) as squareMeterPriceAverage, count(squareMeterPrice) numberOfAds, addedAtDate
from fieldDateAndPrice group by (addedAtDate);




--FLUCTUATION AVERAGES
create view apartmentFluctuation
as
select p.addedAtDate, p.fluctuationInPercentageSinceLastDate
from adprice p
         left join ad a
                   ON a.adId = p.adId AND a.squareMeters = p.squareMeters AND a.pageType = p.pageType
where a.pageType = 'APARTAMENT'
  and p.state = 'VALID'
  and p.fluctuationInPercentageSinceLastDate is not null;

create view apartmentFluctuationAverages as
select avg(fluctuationInPercentageSinceLastDate) as squareMeterPriceAverage,
       count(fluctuationInPercentageSinceLastDate)  numberOfAds,
       addedAtDate
from apartmentFluctuation
group by (addedAtDate);


create view houseFluctuation
as
select p.addedAtDate, p.fluctuationInPercentageSinceLastDate
from adprice p
         left join ad a
                   ON a.adId = p.adId AND a.squareMeters = p.squareMeters AND a.pageType = p.pageType
where a.pageType = 'CASE'
  and p.state = 'VALID'
  and p.fluctuationInPercentageSinceLastDate is not null;

create view houseFluctuationAverages as
select avg(fluctuationInPercentageSinceLastDate) as squareMeterPriceAverage,
       count(fluctuationInPercentageSinceLastDate)  numberOfAds,
       addedAtDate
from houseFluctuation
group by (addedAtDate);


create view fieldFluctuation
as
select p.addedAtDate, p.fluctuationInPercentageSinceLastDate
from adprice p
         left join ad a
                   ON a.adId = p.adId AND a.squareMeters = p.squareMeters AND a.pageType = p.pageType
where a.pageType = 'TEREN'
  and p.state = 'VALID'
  and p.fluctuationInPercentageSinceLastDate is not null;

create view fieldFluctuationAverages as
select avg(fluctuationInPercentageSinceLastDate) as squareMeterPriceAverage,
       count(fluctuationInPercentageSinceLastDate)  numberOfAds,
       addedAtDate
from fieldFluctuation
group by (addedAtDate);



