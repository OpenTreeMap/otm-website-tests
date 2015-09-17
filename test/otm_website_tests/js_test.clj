(ns otm-website-tests.js-test
  (:use midje.sweet)
  (:use [otm-website-tests.core]))

(facts "about /js"

  (fact "it returns a not found from S3 if trailing slash exists"
    (let [resp (head "/js/")]
      (resp :status) => 404
      (s3-response? resp) => true))

  (fact "it returns a not found from S3 if trailing slash is missing"
    (let [resp (head "/js")]
      (resp :status) => 404
      (s3-response? resp) => true))

  (fact "it returns a not found from S3 if an invalid JavaScript file is requested"
    (let [resp (head "/js/joker.js")]
      (resp :status) => 404
      (s3-response? resp) => true))

  (fact "it returns a response from S3 if a valid JavaScript file is requested"
    (let [resp (head "/js/homepage.js")]
      (resp :status) => 200
      (s3-response? resp) => true)))
