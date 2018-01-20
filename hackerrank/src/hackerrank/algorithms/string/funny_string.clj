;;
;; https://www.hackerrank.com/challenges/funny-string/problem
;;
(ns hackerrank.algorithms.string.funny-string)

(defn funny-dat [string]
  (->> (partition 2 1 string)
       (map (fn [[a b]] 
              (Math/abs (- (int b) (int a)))))))

(let [n (Integer/parseInt (read-line))]
  (doseq [_ (range n)]
    (let [string (read-line)
          dat (funny-dat string)
          rev-dat (funny-dat (reverse string))
          ]
      (if (= dat rev-dat)
        (println "Funny")
        (println "Not Funny")
      ))))

