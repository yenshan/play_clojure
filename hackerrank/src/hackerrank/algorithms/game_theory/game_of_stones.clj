;;
;; https://www.hackerrank.com/challenges/game-of-stones-1/problem
;;
(ns hackerrank.algorithms.game-theory.game-of-stones)


(def rm-stone-nums [2 3 5])

(defn win? [n]
  (some #(= % n) rm-stone-nums))

(defn take-stones [n m]
  (if (get m n)
    m
    (cond 
      (<= n 0) false
      (win? n) (assoc m n true)
      :else (let [nn (filter #(> % 0)
                             (map #(- n %) rm-stone-nums))
                  tm (reduce (fn [res d] (take-stones d res))
                             m
                             nn)
                  tr (map #(not (get tm %)) nn)
                  ]
              (if (some true? tr)
                (assoc tm n true)
                tm)))))
      

(defn print-winner [flg]
  (println (if flg "First" "Second")))


(let [T (Integer/parseInt (read-line))
      xs (for [_ (range T)] (Integer/parseInt (read-line)))
      res (reduce #(take-stones %2 %1)
                  {}
                  xs)]
  (doseq [x xs]
    (print-winner (get res x))))
              

