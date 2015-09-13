(ns otm-website-tests.fonts-test
  (:use midje.sweet)
  (:use [otm-website-tests.core])
  (:require [clj-http.client :as client]))

(facts "about /fonts"

  (fact "it returns a not found from S3 if trailing slash exists"
    (let [resp (client/get (str base-url "/fonts/") http-client-options)]
      (resp :status) => 404
      (s3-response? resp) => true))

  (fact "it returns a not found from S3 if trailing slash is missing"
    (let [resp (client/get (str base-url "/fonts") http-client-options)]
      (resp :status) => 404
      (s3-response? resp) => true))

  (fact "it returns a not found from S3 if an invalid font file is requested"
    (let [resp (client/get (str base-url "/fonts/joker.ttf") http-client-options)]
      (resp :status) => 404
      (s3-response? resp) => true))

  (fact "it returns a response from S3 if a valid font file is requested"
    (let [resp (client/get (str base-url "/fonts/glyphicons-halflings-regular.ttf") http-client-options)]
      (resp :status) => 200
      (s3-response? resp) => true)))
