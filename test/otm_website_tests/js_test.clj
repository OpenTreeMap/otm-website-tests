(ns otm-website-tests.js-test
  (:use midje.sweet)
  (:use [otm-website-tests.core])
  (:require [clj-http.client :as client]))

(facts "about /js"

  (fact "it returns a not found from S3 if trailing slash exists"
    (let [resp (client/get (str base-url "/js/") http-client-options)]
      (resp :status) => 404
      (s3-response? resp) => true))

  (fact "it returns a not found from S3 if trailing slash is missing"
    (let [resp (client/get (str base-url "/js") http-client-options)]
      (resp :status) => 404
      (s3-response? resp) => true))

  (fact "it returns a not found from S3 if an invalid JavaScript file is requested"
    (let [resp (client/get (str base-url "/js/joker.js") http-client-options)]
      (resp :status) => 404
      (s3-response? resp) => true))

  (fact "it returns a response from S3 if a valid JavaScript file is requested"
    (let [resp (client/get (str base-url "/js/homepage.js") http-client-options)]
      (resp :status) => 200
      (s3-response? resp) => true)))
