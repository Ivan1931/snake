require 'shoes'

def vertex_coord(vertex)
  components = vertex.split ","
  return components[0].to_i, components[1].to_i
end

def apple_coords( vertex)
  components = vertex.split " "
  return components[0].to_i, components[1].to_i
end

def parse_snake(world, snake_string, snake_num)
  snake_string = snake_string.split " "
  if snake_string.first == "alive"
    add_snake_to_world(world, snake_string[3..-1], snake_num)
  end
end

def add_snake_to_world(world, vertexes, snake_num)
  (vertexes.length - 1).times do |i|
    add_between_vertexes(world, snake_num, vertexes[i], vertexes[i+1])
  end
  add_between_vertexes(world, snake_num, vertexes[-2], vertexes[-1])
  x, y = vertex_coord(vertexes.first)
  world[x][y] = :head
end

def add_between_vertexes(world, snake_num, a, b)
  x1, y1 = vertex_coord a
  x2, y2 = vertex_coord b
  x1, x2 = x2, x1 if x1 > x2
  y1, y2 = y2, y1 if y1 > y2
  if x1 < x2
    (x1..x2).each do |i|
      world[i][y1] = snake_num
    end
  else
    (y1..y2).each do |i|
      world[x1][i] = snake_num
    end
  end
end

snake_num = 0

def read_world(str)
  lines = str.split "\n"
  lines = lines[1..-1] if lines.first.nil?
  world = Array.new(50) {Array.new(50, nil)}
  2.times do |i|
    x,y = apple_coords lines[i]
    world[x][y] = :apple
    world = Array.new(50){ Array.new(50, nil) }
  end
  snake_num = lines[2].to_i
  lines[3..-1].each_with_index do |line, i|
    parse_snake world, line, i
  end
  world
end

Shoes.app do
  @world = read_world(File.read(ARGV.first))
  colors = [blue, green, yellow, orange]
  50.times do |i|
    50.times do |j|
      square = @world[i][j]
      width = 10
      left = width * i
      top = width * j
      if square == :apple
        fill red
        oval(left: left, top: top, width: width)
      elsif square != nil
        if square == snake_num
          fill red
        elsif square == :head
          fill purple 
        else
          fill colors[square]
        end
        rect(top: top, left: left, width: width)
      else
        fill white
        rect(top: top, left: left, width: width)
      end
    end
  end
end
