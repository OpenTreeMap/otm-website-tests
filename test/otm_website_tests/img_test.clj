(ns otm-website-tests.img-test
  (:use midje.sweet)
  (:use [otm-website-tests.core]))

(facts "about /img"

  (fact "it returns a not found from S3 if trailing slash exists"
    (let [resp (head "/img/")]
      (resp :status) => 404
      (s3-response? resp) => true))

  (fact "it returns a not found from S3 if trailing slash is missing"
    (let [resp (head "/img")]
      (resp :status) => 404
      (s3-response? resp) => true))

  (fact "it returns a not found from S3 if an invalid image file is requested"
    (let [resp (head "/img/joker.png")]
      (resp :status) => 404
      (s3-response? resp) => true))

  (fact "it returns a response from S3 if a valid image file is requested"
    (let [resp (head "/img/favicon.png")]
      (resp :status) => 200
      (s3-response? resp) => true)))
