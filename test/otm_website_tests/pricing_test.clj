(ns otm-website-tests.pricing-test
  (:use midje.sweet)
  (:use [otm-website-tests.core]))

(facts "about /pricing"

  (fact "it returns a response from S3 if trailing slash exists"
    (let [resp (head "/pricing/")]
      (resp :status) => 200
      (s3-response? resp) => true))

  (fact "it returns a redirect if trailing slash is missing"
    (let [resp (head "/pricing")]
      (resp :status) => 302
      (get-in resp [:headers :location]) => "/pricing/"))

  (fact "it returns a response from S3 if module.html exists"
    (let [resp (head "/pricing/module.html")]
      (resp :status) => 200
      (s3-response? resp) => true)))
