

def pig_latin(word)
  prefix = word[0, %w(a e i o u).map{|vowel|
    "#{word}aeiou".index(vowel)}.min]
  "#{word[prefix.length..-1]}#{prefix}ay"
end


puts 'Enter your word: '
word = gets.chomp
puts "Your word is #{word}"
puts "Your word #{word} in pig latin is: #{pig_latin(word)}"