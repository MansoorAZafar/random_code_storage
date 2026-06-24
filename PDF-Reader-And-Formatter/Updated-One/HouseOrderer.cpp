#include <string>
#include <iostream>
#include <fstream>
#include <vector>
#include <algorithm>

#define DELIM "|"
#define NUM_OF_PAYS 4
#define COLLECTION_2024_2025_INDEX 1

struct Member {
  
  private:
  struct address {
    int number{};
    std::string street{};
  };
  
  public:

  int memberCode{};
  std::string majlis{};
  std::string name{};
  std::string contact{};
  std::string home_contact{};
  
  // Can be useless or useful...
  std::string note{};
  address addr{};

  std::string membership[4]{};
  std::string ijtima[4]{};

  bool operator<(const Member& other) const {

    std::string thisStreet{this->addr.street.substr(0, other.addr.street.find_first_of(" "))}; 
    std::string otherStreet{other.addr.street.substr(0, other.addr.street.find_first_of(" "))};

    std::transform(thisStreet.begin(), thisStreet.end(), thisStreet.begin(), ::tolower);
    std::transform(otherStreet.begin(), otherStreet.end(), otherStreet.begin(), ::tolower);

    if(thisStreet != otherStreet) {
      return thisStreet < otherStreet;
    }

    return this->addr.number < other.addr.number;
  }  
};

// 
// @line: the current line
// * Gets the word upto a delimiter
// * Deletes the previous context upto the delimiter
// @return: a string from 0 -> delim
std::string GetAndDeleteToDelim(std::string& line, std::string delim=DELIM) {
  const size_t position = line.find_first_of(delim);

  std::string result{line.substr(0, position)};
  // Remove the string and the delimiter
  line.erase(0, position + 1);

  return result;
}

bool isNumber(const std::string& str) {
  try {
    std::stoi(str);
    return true;
  } catch(...) {
    return false;
  }
}

bool isWhiteSpace(const std::string& str)
{
    return std::all_of(str.begin(), str.end(), [](unsigned char ch) {return std::isspace(ch);});
}

void read_hyphen_csv(const char* inputfile, const char* outputfile) {
  std::vector<Member> members{};
  std::ifstream ifstr{inputfile};
  std::ofstream ofstr{outputfile};

  bool ignoreLine{true};

  // Members are read as 2 parts
  // the top part
  // bottom part

  // csv gives as
  // top part
  // [empty line]
  // bottom part
  bool first_part{true}; 

  int next_member{false};
  Member current_member{};

  while(ifstr) {
    /*
    * Skip the line IFF
    *   1. The line is empty or just all whitespace
    *   2. The line doesn't indicate the start of a new member
    *   3. The line is a false positive (theres a number but < 2 length)
    */
    
    std::string line{};
    std::getline(ifstr, line);


    if(line.empty() || !ifstr || isWhiteSpace(line)) {
      continue;
    }
    
    std::string first_word{GetAndDeleteToDelim(line)};
    if(first_word.length() >= 2 && isNumber(first_word)) {
      // The first was a member code
      //  Not a false positive
      ignoreLine = false;
    }

    if(ignoreLine) {
      continue;
    }

    if(first_part) {
      // Extract Member Info
      current_member.memberCode = std::stoi(first_word);
      current_member.majlis = GetAndDeleteToDelim(line);
      current_member.name = GetAndDeleteToDelim(line);
      current_member.contact = GetAndDeleteToDelim(line);
      
      current_member.addr.number = std::stoi(GetAndDeleteToDelim(line, " "));
      current_member.addr.street = GetAndDeleteToDelim(line);
      
      // Ignore the Membership line
      GetAndDeleteToDelim(line);

      //Count number of delimiters here
      std::string lncpy{line};
      int cnt{};
      size_t pos{0};
      while((pos = lncpy.find(DELIM)) != std::string::npos) {
        ++cnt;
        lncpy.erase(pos, 1);
      }
      
      if(cnt == 1) {
        line = "0" + std::string(DELIM) + line + std::string(DELIM) + "0";
      } else if(lncpy.substr(lncpy.length() - 1, lncpy.length()) == DELIM) {
        line = line + "0";
      } else if(cnt == 2) {
        line = "0" + std::string(DELIM) + line;
      }

      for(int i = 0; i < NUM_OF_PAYS; ++i) {
        
        std::string curr = GetAndDeleteToDelim(line);
        if(!isWhiteSpace(curr)) {
          if(curr[0] == '$') {
            curr = curr.substr(1, curr.length());
          }
          current_member.membership[i] = curr;
        } else {
          current_member.membership[i] = "0";
        }
      }

      first_part = false;
    } else {
      // Ignore the blank and other uneeded part
      GetAndDeleteToDelim(line);
      GetAndDeleteToDelim(line);

      current_member.home_contact = GetAndDeleteToDelim(line);
      current_member.addr.street += " " + GetAndDeleteToDelim(line);

      GetAndDeleteToDelim(line);

      //Count number of delimiters here
      std::string lncpy{line};
      int cnt{};
      size_t pos{0};
      while((pos = lncpy.find(DELIM)) != std::string::npos) {
        ++cnt;
        lncpy.erase(pos, 1);
      }
      
      if(cnt == 1) {
        line = "0" + std::string(DELIM) + line + std::string(DELIM) + "0";
      } else if(lncpy.substr(lncpy.length() - 1, lncpy.length()) == DELIM) {
        line = line + "0";
      } else if(cnt == 2) {
        line = "0" + std::string(DELIM) + line;
      }


      for(int i = 0; i < NUM_OF_PAYS; ++i) {
        std::string curr = GetAndDeleteToDelim(line);
        if(!isWhiteSpace(curr) && curr != "-") {
          if(curr[0] == '$') {
            curr = curr.substr(1, curr.length());
          }
          current_member.ijtima[i] = curr;
        } else {
          current_member.ijtima[i] = "0";
        }
      }

      first_part = true;
      ignoreLine = true;

      members.push_back(current_member);
    }
  }

  std::cout << "Successfully captured all users\n " \
  "Now, Writing to " << outputfile << "\n";
  
  std::sort(members.begin(), members.end());

  for(const auto& member : members) {
    ofstr 
    << member.memberCode
    << "|"
    << member.name
    << "|"
    << member.contact
    << "|"
    << member.home_contact
    << "|"
    << member.addr.number
    << " "
    << member.addr.street
    << "|"
    << member.membership[COLLECTION_2024_2025_INDEX]
    << "|"
    << member.ijtima[COLLECTION_2024_2025_INDEX] 
    << "\n";
  }

  std::cout << "Successfully wrote members to " 
  << outputfile << "\n";

}

int main(int argc, char** argv) {
  if(argc < 3) {
    read_hyphen_csv("input.csv", "output.csv");
  } else {
    read_hyphen_csv(argv[0], argv[1]);
  }
}
