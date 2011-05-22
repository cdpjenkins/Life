(ns life.core)

(import 
 '(java.awt Color Graphics Dimension FlowLayout)
 '(javax.swing JPanel JFrame JButton Timer BoxLayout)
 '(java.awt.event MouseListener ActionListener ActionEvent))

;(set! *warn-on-reflection* true)

(def width  50)
(def height 20)
(def cell-width 10)
(def cell-height 10)

(def sleep-time 100)

(def initial-board
;     (apply vector (replicate height
;		     (apply vector (replicate width 0)))))
     [[0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0] [0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0] [0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0] [0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0] [0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 1 0 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0] [0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 1 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 1 1 0 1 0 0 0 0 1 1 0 0 0 0 0 0 0 0] [0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 1 0 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 1 1 0 1 1 0 0 0 1 1 0 0 0 0 0 0 0 0] [0 0 0 0 0 0 1 1 0 0 1 1 0 0 0 0 0 0 1 0 0 0 0 0 0 0 0 0 0 0 0 0 1 1 0 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0] [0 0 0 0 0 0 1 1 0 1 0 0 1 0 0 1 0 0 1 0 0 0 0 0 0 0 0 0 0 0 0 0 1 0 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0] [0 0 0 0 0 0 0 0 0 0 1 1 0 0 0 0 0 0 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0] [0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 1 0 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0] [0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 1 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0] [0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0] [0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0] [0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0] [0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0] [0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0] [0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0] [0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0] [0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0]])

(def board-atom (atom initial-board ))

(def running-atom (atom false))

;; Board manipulation functions

;(defn print-board [b]
;  (doseq [row b]
;    (println (str row))))

(defn within [v min max]
  (and (>= v min) (< v max)))

(defn cell-at [b x y]
  (if (and (within x 0 width) (within y 0 height))
    (nth (nth b y) x)
    0))

(defn occurences [l item]
  (count (filter #(= % item) l)))

(defn new-cell-value [cell neighbours]
  (let [num-1s (occurences neighbours 1)]
    (if (= cell 0)
      ; cell is currently dead and will become alive iff it has 3 living
      ; neighbours
      (if (= num-1s 3) 1 0)
      ; else cell is currently alive and will die iff it has fewer than 2 or
      ; more than 3 living neighbours
      (if (or (< num-1s 2) (> num-1s 3)) 0 1))))

(defn cell-neighbours [b x y]
  [(cell-at b (- x 1) (- y 1))
   (cell-at b x (- y 1))
   (cell-at b (+ x 1) (- y 1))
   (cell-at b (- x 1) y)
   (cell-at b (+ x 1) y)
   (cell-at b (- x 1) (+ y 1))
   (cell-at b x (+ y 1))
   (cell-at b (+ x 1) (+ y 1))])

(defn new-value-at [b x y]
  (let [cell (cell-at b x y)
	neighbours (cell-neighbours b x y)]
    (new-cell-value cell neighbours)))

(defn new-board [b]
  (vec (for [y (range height)]
    (vec (for [x (range width)]
      (new-value-at b x y))))))

(defn flip-cell [b x y]
  (update-in b [y x] #(- 1 %)))

(defn apply-n-times [f n x]
  (if (zero? n)
    x
    (recur f (dec n) (f x))))

;; GUI functions

(defn make-action-listener [f]
  (proxy [ActionListener] [] (actionPerformed [e] (f e))))

(defn render [#^Graphics g b]
  (doseq [y (range height) x (range width)]
    (let [cell (cell-at b x y)]
      (if (= cell 0)
	(.setColor g Color/WHITE)
	(.setColor g Color/BLACK))
      (.fillRect g (* x cell-width) (* y cell-height)
		 (dec cell-width) (dec cell-height)))))

(defn make-board-panel []
  (let [panel 
	(proxy [JPanel MouseListener] []
	  (paint [g] (render g @board-atom))
	  (getPreferredSize [] (Dimension. (* width cell-width) 
					   (* height cell-height))))]
    (doto panel
      (.addMouseListener (proxy [MouseListener] []
	  (mouseClicked [e]
	    (let [x (int (/ (.getX e) cell-width))
		  y (int (/ (.getY e) cell-height))]
	      (swap! board-atom flip-cell x y)))
	  (mousePressed [e])
	  (mouseReleased [e])
	  (mouseEntered [e])
	  (mouseExited [e]))))))

(defn make-button [text action]
  (doto (JButton. text)
    (.addActionListener (make-action-listener action))))

(defn make-control-panel []
  (let [control-panel (JPanel.)]
    (doto control-panel
      (.setLayout (BoxLayout. control-panel BoxLayout/Y_AXIS))
      (.add (make-button "Start" (fn [e]
				   (swap! running-atom (constantly true)))))
      (.add (make-button "Stop" (fn [e]
				  (swap! running-atom (constantly false)))))
      (.add (make-button "Step" (fn [e]
				  (swap! board-atom new-board)))))))

(defn make-frame []
  (let [frame (JFrame. "Life")]
    (doto (.getContentPane frame)
      (.setLayout (FlowLayout.))
      (.add (make-board-panel))
      (.add (make-control-panel)))
    (add-watch board-atom "repaint" (fn [k r o n] (.repaint frame)))
    (doto frame
      (.pack)
      (.show))
    frame))

(defn go [b]
  (let [frame (make-frame)]
    (.start (Timer. sleep-time (make-action-listener
				(fn [e] (when @running-atom
					  (swap! b new-board))))))))
(go board-atom)

