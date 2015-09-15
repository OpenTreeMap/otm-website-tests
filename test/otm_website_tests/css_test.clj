(ns otm-website-tests.css-test
  (:use midje.sweet)
  (:use [otm-website-tests.core]))

(facts "about /css"

  (fact "it returns a not found from S3 if trailing slash exists"
    (let [resp (head "/css/")]
      (resp :status) => 404
      (s3-response? resp) => true))

  (fact "it returns a not found from S3 if trailing slash is missing"
    (let [resp (head "/css")]
      (resp :status) => 404
      (s3-response? resp) => true))

  (fact "it returns a not found from S3 if an invalid CSS file is requested"
    (let [resp (head "/css/joker.css")]
      (resp :status) => 404
      (s3-response? resp) => true))

  (fact "it returns a response from S3 if a valid CSS file is requested"
    (let [resp (head "/css/main.css")]
      (resp :status) => 200
      (s3-response? resp) => true))

  (fact "it returns a response from the application if path begins with /css"
    (let [resp (head "/cssmap")]
      (resp :status) => 404
      (s3-response? resp) => false)))
