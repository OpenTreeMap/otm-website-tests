(ns otm-website-tests.img-test
  (:use midje.sweet)
  (:use [otm-website-tests.core])
  (:require [clj-http.client :as client]))

(facts "about /img"

  (fact "it returns a not found from S3 if trailing slash exists"
    (let [resp (client/get (str base-url "/img/") http-client-options)]
      (resp :status) => 404
      (s3-response? resp) => true))

  (fact "it returns a not found from S3 if trailing slash is missing"
    (let [resp (client/get (str base-url "/img") http-client-options)]
      (resp :status) => 404
      (s3-response? resp) => true))

  (fact "it returns a not found from S3 if an invalid image file is requested"
    (let [resp (client/get (str base-url "/img/joker.png") http-client-options)]
      (resp :status) => 404
      (s3-response? resp) => true))

  (fact "it returns a response from S3 if a valid image file is requested"
    (let [resp (client/get (str base-url "/img/favicon.png") http-client-options)]
      (resp :status) => 200
      (s3-response? resp) => true)))
