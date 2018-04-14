(ns word-count.core
  (:require [clojure.java.io :as io]
            [clojure.string :as str])
  (:gen-class))

(defn count-words
  [line]
  (let [w (-> line
              (str/replace #"^\s+" "")
              (str/split #"[\s\t]+"))]
    (if (or (= line "") (= w [""]))
      0
      (count w)))) 

(defn -main
  [& args]
  (let [f (if-not (empty? args)  
            (io/file (first args))
            *in*)
        dat (line-seq (io/reader f))]
    (println (str "lines: " (count dat)))
    (println (str "words: " (reduce + (map count-words dat))))))
