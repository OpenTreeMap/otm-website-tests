(ns otm-website-tests.css-test
  (:use midje.sweet)
  (:use [otm-website-tests.core])
  (:require [clj-http.client :as client]))

(facts "about /css"

  (fact "it returns a not found from S3 if trailing slash exists"
    (let [resp (client/get (str base-url "/css/") http-client-options)]
      (resp :status) => 404
      (s3-response? resp) => true))

  (fact "it returns a not found from S3 if trailing slash is missing"
    (let [resp (client/get (str base-url "/css") http-client-options)]
      (resp :status) => 404
      (s3-response? resp) => true))

  (fact "it returns a not found from S3 if an invalid CSS file is requested"
    (let [resp (client/get (str base-url "/css/joker.css") http-client-options)]
      (resp :status) => 404
      (s3-response? resp) => true))

  (fact "it returns a response from S3 if a valid CSS file is requested"
    (let [resp (client/get (str base-url "/css/main.css") http-client-options)]
      (resp :status) => 200
      (s3-response? resp) => true))

  (fact "it returns a response from the application if path begins with /css"
    (let [resp (client/get (str base-url "/cssmap") http-client-options)]
      (resp :status) => 404
      (s3-response? resp) => false)))
