#lang racket
(require plot)

;; constant time O(1)
(define (five)
  (+ 1 1 1 1 1))

;; linear time O(N)
(define (fac N)
  (if (< N 1)
      1
      (* N (fac (- N 1)))
      ))

;; quadratic time O(n^2)
;; (bubble-sort < (for/vector ([_ 10]) (random 20)))
(define (bubble-sort <? v)
  (define len (vector-length v))
  (define ref vector-ref)
  (let loop ([max len] 
             [again? #f])
    (for ([i (in-range 0 (- max 1))]
          [j (in-range 1 max)])
      (define vi (ref v i))
      (when (<? (ref v j) vi)
        (vector-set! v i (ref v j))
        (vector-set! v j vi)
        (set! again? #t)))
    (when again? (loop (- max 1) #f)))
  v)

;; Logarithmic Time O(log2N) 
(define (halve-count N)
  (if (equal? N 1)
      0
      (+ (halve-count (quotient N 2)) 1)))

;; Exponential time O(2N)
(define (fib n)
  (if (< n 2)
      n
      (+ (fib (- n 1))
         (fib (- n 2)))))
