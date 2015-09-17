(ns otm-website-tests.fonts-test
  (:use midje.sweet)
  (:use [otm-website-tests.core]))

(facts "about /fonts"

  (fact "it returns a not found from S3 if trailing slash exists"
    (let [resp (head "/fonts/")]
      (resp :status) => 404
      (s3-response? resp) => true))

  (fact "it returns a not found from S3 if trailing slash is missing"
    (let [resp (head "/fonts")]
      (resp :status) => 404
      (s3-response? resp) => true))

  (fact "it returns a not found from S3 if an invalid font file is requested"
    (let [resp (head "/fonts/joker.ttf")]
      (resp :status) => 404
      (s3-response? resp) => true))

  (fact "it returns a response from S3 if a valid font file is requested"
    (let [resp (head "/fonts/glyphicons-halflings-regular.ttf")]
      (resp :status) => 200
      (s3-response? resp) => true)))
