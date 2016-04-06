(ns rand-r-circle-anime.core
  (:use seesaw.core
        seesaw.graphics
        seesaw.dev)
  (:gen-class))

(defmacro defexample 
  [arg-vec & body]
  `(do
     (defn ~'run [on-close# & args#]
       (let [~arg-vec args#
             f# (invoke-now ~@body)]
         (config! f# :on-close on-close#)
         (when (= (java.awt.Dimension.) (.getSize f#))
           (pack! f#))
         (show! f#)))
     (defn ~'-main [& args#]
       (apply ~'run :exit args#))))


(def style1
  (style :foreground "#ff0000"
         :stroke (stroke :width 2 :cap :round)))

(defn paint1
  [c g]
  (let [width (.getWidth c)
        height (.getHeight c)
        cpx (/ width 2)
        cpy (/ height 2)
        obj (circle cpx cpy (rand (/ width 2)))]
    (draw g obj style1)))

(def main-canvas
  (canvas :id :canvas
          :background "#BBBBBB"
          :size [400 :by 400]
          :paint paint1))

(def main-window
  (frame :title "TestCanvas"
         :content main-canvas
         :width 400 :height 400
         :on-close :exit))

(defexample []
  (let [t (timer (fn [e] (repaint! main-canvas)) :delay 1000)]
    (frame 
      :title "Seesaw Canvas Clock" 
      :width 400 :height 400
      :content main-canvas)))

(defn -main[]
  (run :dispose))
