def make_into_java(str)
    strs = str.split "\n"
    strs.each_with_index do |line, i|
      if i < (strs.length - 1)
        print "\"#{line}\", "
      else
        print "\"#{line}\""
      end
    end
end

file = File.read ARGV.first

split_file_name = ARGV.first.split("-")

fname = "#{split_file_name[2]}_#{split_file_name[3]}"

print "private String[] testDataFile#{fname} = {"
make_into_java(file)
print " };"

puts ""
puts "public void testDecisionForData#{fname}() {"
puts "testForState(testDataFile#{fname}, );"
puts "}"
