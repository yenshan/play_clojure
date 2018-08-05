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


(defn make-shiritori [res ys]
  (apply concat 
         (for [y ys]
           (if (or (empty? res)
                   (shiritori? (first res) y))
             (make-shiritori (cons y res) 
                             (remove #(= % y) ys))
             [res]))))

(->> countries
     (map #(s/upper-case %))
     (make-shiritori [])
     (sort-by count #(compare %2 %1))
     first
     count
     println)

