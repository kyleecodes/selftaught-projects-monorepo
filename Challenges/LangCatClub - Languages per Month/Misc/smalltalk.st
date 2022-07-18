constant (even or odd built ins)
Number>>even
	^((self digitAt: 1) bitAnd: 1) = 0
5 even
5 odd


linear (factorial)
Number extend [
  my_factorial [
    self < 0 ifTrue: [ self error: 'my_factorial is defined for natural numbers' ].
    self isZero ifTrue: [ ^1 ].
    ^self * ((self - 1) my_factorial)
  ]
].




quadratic (bubble sort)
|item swap itemCount hasChanged|
item := #(1 4 5 6 10 8 7 61 0 -3) copy.
swap := 
	[:indexOne :indexTwo| 
	|temp|
	temp := item at: indexOne.
	item at: indexOne put: (item at: indexTwo).
	item at: indexTwo put: temp].
 
itemCount := item size.
[hasChanged := false.
itemCount := itemCount - 1.
1 to: itemCount do:
	[:index | 
	(item at: index) > (item at: index + 1) ifTrue:
		[swap value: index value: index + 1.
		hasChanged := true]].
hasChanged] whileTrue.


exponential (fibonacci)
|fibo|
fibo := [ :i |
   |ac t|
   ac := Array new: 2.
   ac at: 1 put: 0 ; at: 2 put: 1.
   ( i < 2 )
     ifTrue: [ ac at: (i+1) ]
     ifFalse: [
        2 to: i do: [ :l |
          t := (ac at: 2).
          ac at: 2 put: ( (ac at: 1) + (ac at: 2) ).
          ac at: 1 put: t
        ].
        ac at: 2.
     ]
].
 
0 to: 10 do: [ :i |
  (fibo value: i) displayNl
]



|fibonacci|
fibonacci := [ :i |
   |ac t|
   ac := Array new: 2.
   ac at: 1 put: 0 ; at: 2 put: 1.
   ( i < 2 )
     ifTrue: [ ac at: (i+1) ]
     ifFalse: [
        2 to: i do: [ :l |
          t := (ac at: 2).
          ac at: 2 put: ( (ac at: 1) + (ac at: 2) ).
          ac at: 1 put: t
        ].
        ac at: 2.
     ]
].
 
0 to: 10 do: [ :i |
  (fibonacci value: i) displayNl
]


isPalindrome := [:aString |
	str := (aString select: [:chr| chr isAlphaNumeric]) collect: [:chr | chr asLowercase].
	str = str reversed.
	].


1 to: 100 do: [:n | |r| 
	r := n rem: 15.
	Transcript show: (r isZero 
	   ifTrue:['fizzbuzz'] 
	   ifFalse: [(#(3 6 9 12) includes: r) 
		ifTrue:['fizz'] 
		ifFalse:[((#(5 10) includes: r)) 
			ifTrue:['buzz'] 
			ifFalse:[n]]]); 
	cr].