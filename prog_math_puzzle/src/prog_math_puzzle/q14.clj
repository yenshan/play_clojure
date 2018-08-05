(ns prog-math-puzzle.q14
  (:require [clojure.string :as s]))

(def countries ["Brazil" "Croatia" "Mexico"
                "Cameroon" "Spain" "Netherlands"
                "Chile" "Australia" "Colombia"
                "Greece" "Cote d'lvoire" "Japan"
                "Urguay" "Costa Rica" "England"
                "Italy" "Switzerland" "Ecuador"
                "France" "Honduras" "Aggentina"
                "Bosnia and Herzegovina" "Iran" "Nigeria"
                "Germany" "Portugal" "Ghana"
                "USA" "Beigium" "Algeria"
                "Russia" "Korea Republic"])

(defn shiritori? [st1 st2]
  (= (last st1) (first st2)))


(defn max-combs
  ([xs] (max-combs xs []))
  ([xs res]
   (if (and (<= 2 (count res)) 
            (not (shiritori? (second res) (first res))))
     [(rest res)]
     (apply concat (for [c xs]
                     (max-combs (remove #(= c %) xs)
                                (cons c res)))))))

(->> countries
     (map #(s/upper-case %))
     max-combs
     (sort-by count #(compare %2 %1))
     first
     count
     println)

