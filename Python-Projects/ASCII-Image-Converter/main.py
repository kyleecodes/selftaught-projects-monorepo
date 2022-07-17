import PIL.Image


ASCII_CHARS = ("@", "#", "$", "%", "?", "*", "+", ";", ":", ",", ".")


# set width and then create new height based on ratio
def resize(image, new_width=50):
    # (size() returns 2-tuple width height of pixels)
    width, height = image.size
    print(f"Image width, height: {image.size}")
    if height > width:
        ratio = width / height
        print(f"Height greater ratio: {ratio}")
    elif height < width:
        ratio = height / width
        print(f"Width greater ratio: {ratio}")
    # calculate new height based on new width and ratio
    new_height = int(new_width * ratio)
    print(f"New image width. height: {new_width}, {new_height}")
    return image.resize((new_width, new_height))


def to_greyscale(image):
    return image.convert("L")


def pixel_to_ascii(image):
    pixels = image.getdata()
    print(f"Pixels data: {pixels}")
    # pixel // 25 to make index an int of the ASCII CHARS list and assess for correct contrast (it works)
    # (The // operator is used for truncating division)
    ascii_str = "".join([ASCII_CHARS[pixel // 25] for pixel in pixels])
    return ascii_str


def print_image(greyscale_image):
    ascii_str = pixel_to_ascii(greyscale_image)
    img_width = greyscale_image.width
    ascii_str_len = len(ascii_str)
    # ascii_img is the official list of ascii characters printed into new image
    ascii_img = ""
    # Split the string based on width
    for i in range(0, ascii_str_len, img_width):
        ascii_img += ascii_str[i:i + img_width] + "\n"
    with open("ascii_image.txt", "w") as f:
        f.write(ascii_img)


def main(path):
    image = to_greyscale(resize(PIL.Image.open(path)))
    print_image(image)
    image = PIL.Image.open(path)
    image = resize(image)
    greyscale_image = to_greyscale(image)
    print_image(greyscale_image)


if __name__ == '__main__':
    path = "test_images/bw.jpg"
    main(path)
