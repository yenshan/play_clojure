;;
;; https://www.hackerrank.com/challenges/encryption/problem
;;
(ns hackerrank.algorithms.implementation.encription)

(defn pos->index [x y w]
  (+ x (* y w)))

(let [dat (read-line)
      text (to-array (apply str (remove #(= % \space) dat)))
      len (count text)
      fl (long (Math/floor (Math/sqrt len)))
      cl (long (Math/ceil (Math/sqrt len)))
      rows (if (< (* fl cl) len) cl fl)
      cols cl
      res-text (for [i (range cols)
                     j (range rows)
                     :let [subtxt (get text (pos->index i j cols))
                           blank (if (= j (dec rows)) " " "")]
                     ]
                     (str subtxt blank))
          ]
  (println (apply str res-text)))

      
