(ns others.simple-regex
  (:require [clojure.string :as s]))

(defn match-here [regex string])

(defn match-repeat [r regex [c & rstr]]
  (cond (nil? c) false
        (= (first regex) c) (match-here (rest regex) rstr)
        (or (= r c) (= r \.)) (match-repeat r regex rstr)
        :else false))

(defn match-here 
  [[a b & rst :as regex] [c & rstr]]
  (cond 
    (nil? a) true
    (and (= a \$) (nil? b) (nil? c)) true
    (or (= a c) (= \. a)) (if (= \* b)
                            (match-repeat a rst rstr)
                            (recur (rest regex) rstr))
    :else false))

(defn match [regex string]
  (cond 
    (or (empty? regex) (empty? string)) false
    (= \^ (first regex)) (match-here (rest regex) string)
    (match-here regex string) true
    :else (recur regex (rest string))))


(println (match "^a.*c" "aabcx"))

