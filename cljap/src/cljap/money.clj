(ns cljap.money
  (:require [clojure.test :refer :all]))

(declare validate-same-currency)

(defrecord Currency [divisor sym desc])

(defrecord Money [amount ^Currency currency]
  java.lang.Comparable
  (compareTo [m1 m2]
    (validate-same-currency m1 m2)
    (compare (:amount m1) (:amount m2))))

(def currencies {:usd (->Currency 100 "USD" "US Dollars")
                 :eur (->Currency 100 "EUR" "Euro")})

(defn- validate-same-currency
  [m1 m2]
  (when-not (= (:currency m1) (:currency m2))
    (throw
      (ex-info "Currencies do not match."
               {:m1 m1 :m2 m2}))))

(defn =$
  ([m1] true)
  ([m1 m2] (zero? (.compareTo m1 m2))))

(defn +$
  ([m1] m1)
  ([m1 m2]
   (validate-same-currency m1 m2)
   (->Money (+ (:amount m1) (:amount m2)) (:currency m1)))
  ([m1 m2 & monies]
   (reduce +$ m1 (conj monies m2))))

(defn *$ [m n] (->Money (* n (:amount m)) (:currency m)))

(defn make-money
  ([] (make-money 0))
  ([amount] (make-money amount (:usd currencies)))
  ([amount currency] (->Money amount currency)))
   
(make-money)
; -> #cljap.money.Money{:amount 0, :currency #cljap.money.Currency{:divisor 100, :sym "USD", :desc "US Dollars"}}

(deftest test-money
  (testing "test =$"
    (is (=$ (make-money 100) (make-money 100)))
    (is (not (=$ (make-money 10) (make-money 20))))
    ;(is (not (=$ (make-money 10) (make-money 10 (:eur currencies)))))
    )
  (testing "test +$"
    (is (= 30 (:amount (+$ (make-money 10) (make-money 20)))))
    )
  )

