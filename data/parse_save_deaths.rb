def save_state(file_group_name, file_id, content)
  out_file = File.open "#{file_group_name}-#{file_id}", "w"
  out_file.puts content
  out_file.close
end

data_file = File.read ARGV[0]

states = data_file.split "="

Dir.chdir "deaths"

states.each_with_index do |state, i|
  if state != ""
    save_state ARGV[1], i, state
  end
end
