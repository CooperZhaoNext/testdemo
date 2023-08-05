#!/bin/bash

arr=(
#       "shipper-admin-bff"
#       "ShipperService"
#       "template-service"
#       "DashboardService"
#       "ap-service"
#       "exception-management-service"
#       "next-mobile-service"
#       "comms-service2"
#       "accounting-service"
#       "TextMessageService"
       "rule-engine-service"
       "document-obligation-service"
       "CacheService"
       "CrawlerService"
       "comms-service"
       "GeoLocationService"
       "appointments-service"
       "rmis-integration-service"
       "atlas-edi-service"
       "places-service"
       "ErosService"
       "accessorial-event-service"
       "webscraping-scheduler-service"
       "scraping"
       "atlas-report-service"
       "MailService"
       "user-management-service"
       "expr-resolver-service"
       "equipment-service"
       "shipper-portal-bff"
       "marketplace-job-distribution"
       "YardService"
       "Marketplace-qualifications-service"
       "freight-doc-service")
base_path="/Users/cooper.zhao/project/allrepos/"
for value in ${arr[@]}
do
   urlPath=$base_path$value
   cd "$urlPath"
   pwd
   sleep 5
   gh pr create -t "Add api authorization checking config" -b "Scan all the apis, find the apis that have no permission protection. https://nexttrucking.atlassian.net/browse/APD-29847" -B master -H api-authorization-check
done


