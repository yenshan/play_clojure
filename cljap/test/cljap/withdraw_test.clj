(ns cljap.withdraw-test
  (:require [clojure.test :refer :all]
            [cljap.withdraw :refer :all]))

(deftest test-withdraw
  (testing "make-withdraw"
    (let [W (make-withdraw 100)]
      (is (= 50 (W 50)))
      (is (= 20 (W 30)))
    ))
  (testing "make-account"
    (let [A (make-account 200)]
      (is (= 20 ((A 'withdraw) 180)))
      (is (= 200 ((A 'deposit) 180)))
      )
    ))
