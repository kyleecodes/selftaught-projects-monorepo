;; constant time O(1)
(def addfive
        (+ 1 1 1 1 1))


;; linear time O(N)
(def factorialcombo
  (fn [x]
    (if (= x 1)
      x
      (* x (factorialcombo (- x 1))))))

;; Equivalents: first does not have combo step
(def factorial (fn [x n]
                 (if (= x 1)
                   n
                   (factorial (- x 1)(* n x)))))

(factorialcombo 6)
(factorial 6 1)
(factorial 5 6)
(factorial 4 30)

;;Equivalents: picking up factorials at different steps-
(* 6 (factorialcombo 5))
(* 6 5 (factorialcombo 4))
(* 6 5 4 (factorialcombo 3))


;; quadratic time O(n^2)
(defn bubble-sort [xs]
  (let [ys (reduce bubble [] xs)]
    (if (= xs ys)
      xs
      (recur ys))))

;; Get-index function defintion & invocation (first, rest of list methods)
(def get-index
  (fn [x n]
    (if (= n 0)
      (first x)
      (get-index
        (rest x)
        (dec n)))))

(get-index [1 2 3] 1)
(get-index [:a :b :c] 2)
(get-index [1 2 3] 4)


;; Extra practice: making a ho dress
(defn dress
  [len cut design material]
  (vector len cut design material))

(def ho-dress
  (dress 36 :club :black :silk))

(length? ho-dress)

(rest ho-dress)

(defn second [list]
  (first (rest list)))

(cut? ho-dress)

(defn third [list]
  (first (rest (rest list))))

(design? ho-dress)

(third ho-dress)

(rest (rest ho-dress))

(rest (rest (rest ho-dress)))
p
(defn fourth [list]
  (first (rest (rest (rest list)))))

(material? ho-dress)

(def design (fn [dress]
              (first (rest (rest (rest dress))))))

(rest [1])


(def get-index
  (fn [x n]
    (if (= n 0)
      (first x)
      (get-index
        (rest x)
        (dec n)))))

(get-index [1 2 3] 1)
(get-index [:a :b :c] 2)
(get-index [1 2 3] 4)


;;(defn material? [d]
;;(fourth d))

(defn material? [d]
  (get-index d 3))

(material? ho-dress)

;;(defn design? [d]
;;(third d))

(defn design? [d]
  (get-index d 2))

(design? ho-dress)

;;(defn cut? [d]
;;(second d))

(defn cut? [d]
  (get-index d 1))

(cut? ho-dress)

;;(defn length? [d]
;;(first d))

(defn length? [d]
  (get-index d 0))

(length? ho-dress)