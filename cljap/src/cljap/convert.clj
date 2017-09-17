(ns cljap.convert
  (:require [clojure.test :refer :all]))

(defmulti convert
  "Convert quantity from unit1 to unit2, matching on [unit1 unit2]"
  (fn [unit1 unit2 quantity] [unit1 unit2]))

(defmethod convert [:lb :oz] [_ _ lb] (* lb 16))

(defmethod convert [:oz :lb] [_ _ oz] (/ oz 16))

(defmethod convert :default [u1 u2 q]
  (if (= u1 u2)
    q
    (assert false (str "Unkown unit conversion from " u1 " to " u2))))

(defn ingredient+
  "Add two ingredients into a single ingredient, combining their
  quantities with unit conversion if necessary."
  [{q1 :quantity u1 :unit :as il} {q2 :quantity u2 :unit}]
  (assoc il :quantity (+ q1 (convert u2 u1 q2))))

(defrecord Ingredient [name quantity unit])


(deftest test-convert
  (testing "test ingredient+"
    (is (= (->Ingredient "Spaghetti" 3/4 :lb)
           (ingredient+ (->Ingredient "Spaghetti" 1/2 :lb)
                        (->Ingredient "Spaghetti" 4 :oz))))
    ))
