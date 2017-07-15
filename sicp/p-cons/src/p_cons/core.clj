(ns p-cons.core
  (:gen-class))

;;
;; cons implemented only by procedure
;;

(defn mycons [x y]
  (letfn [(dispatch [m]
            (cond (= m 0) x
                  (= m 1) y
                  :else (println "Argument not 0 or 1 -- CONS" m)))]
    dispatch))

(defn car [z] (z 0))
(defn cdr [z] (z 1))

(defn -main
  [& args]
  (let [a (mycons 10 12)]
    (println a)
    (println "car a = " (car a))
    (println "cdr a = " (cdr a))))
