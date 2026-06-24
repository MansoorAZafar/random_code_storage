#include <iostream>
#include <algorithm>
#include <fstream>
#include <vector>
#include <string>
#include <sstream>

void printStrAndContinue(std::string str)
{
    int x{};
    std::cout << "Str is : " << str << "\n\n";
    std::cin >> x;
}

struct Member 
{
    std::string m_name{};
    std::string m_phoneNum{};
    std::string Mbr_Amt[5]{};
    int m_member_code{};
    int m_street_num{};
    std::string m_street_name{};

    bool operator<(const Member& obj) const 
    {
        std::string thisStreet{this->m_street_name}; 
        std::string objStreet{obj.m_street_name};

        std::transform(thisStreet.begin(), thisStreet.end(), thisStreet.begin(), ::tolower);
        std::transform(objStreet.begin(), objStreet.end(), objStreet.begin(), ::tolower);

        if (thisStreet.find("nasir") != std::string::npos)
        {
            if (objStreet.find("nasir") == std::string::npos) return true;
            return this->m_street_num < obj.m_street_num;
        }
        if (thisStreet.find("tahir") != std::string::npos)
        {
            if (objStreet.find("nasir") != std::string::npos) return false;
            if (objStreet.find("tahir") == std::string::npos) return true;
            return this->m_street_num < obj.m_street_num;
        }
        if (thisStreet.find("ahmadiyya") != std::string::npos)
        {
            if (objStreet.find("nasir") != std::string::npos) return false;
            if (objStreet.find("tahir") != std::string::npos) return false;
            return this->m_street_num < obj.m_street_num;
        }
        if (objStreet.find("nasir") == std::string::npos &&
            objStreet.find("tahir") == std::string::npos &&
            objStreet.find("ahmadiyya") == std::string::npos) 
        {
            return true;
        }
        return this->m_street_num < obj.m_street_num;
    }


    friend std::ostream& operator<<(std::ostream& ostr, Member person)
    {
        return ostr << "mem_code:  " << person.m_member_code
                    << "\nname:  " << person.m_name
                    << "\nphoneNum:  " << person.m_phoneNum
                    << "\nstreet_name:  " << person.m_street_name 
                    << "\nstreet num:  " << person.m_street_num
                    << "\n\n";
    }
};

bool isWhiteSpace(const std::string& str)
{
    return std::all_of(str.begin(), str.end(), [](unsigned char ch) {return std::isspace(ch);});
}

void addToFile(std::ofstream& ofstr, Member member)
{
    ofstr << member.m_member_code << "," << member.m_name << "," 
        << member.m_street_num << " " <<  member.m_street_name << 
        "," << member.m_phoneNum << "," << member.Mbr_Amt[0] << ","
        << member.Mbr_Amt[1] << "," << member.Mbr_Amt[2] << ","
        << member.Mbr_Amt[3] << "," << member.Mbr_Amt[4] << "\n";
}

void read_new_file_updated(const char* inputFile = "input.txt", const char* outputFile = "raw_output.csv")
{
    std::vector<Member> members{};
    std::ifstream ifstr{inputFile};
    std::ofstream ofstr{outputFile};
    int line_count{1};
    bool all_info{false};
    Member current_member{};

    while(ifstr)
    {
        std::string line{};
        std::getline(ifstr, line);

        if(line.empty() || !ifstr || isWhiteSpace(line))
        {
            line_count = 1;
            continue;
        }

        std::istringstream iss{line};

        if (line_count == 1)
        {
            iss >> current_member.m_member_code;
            line.erase(0, iss.tellg());

            std::string tmp{};
            while(tmp.find("-") == std::string::npos)
            {
                current_member.m_name += tmp + " ";
                line.erase(0, iss.tellg());
                iss >> tmp;
            }            

            current_member.m_phoneNum = tmp;
            line.erase(0, iss.tellg());
            iss >> tmp; // remove the ______'s 
            line.erase(0, iss.tellg());

            for(int i = 0; i < 5; ++i)
            {
                iss >> current_member.Mbr_Amt[i];
                line.erase(0, iss.get());
            }
        }

        if(line_count == 2)
        {
            iss >> current_member.m_street_num;
            line.erase(0, iss.tellg());

            iss >> current_member.m_street_name;
            all_info = true;
        }

        iss.clear();
        if(++line_count == 3) line_count = 1;
        if(all_info)
        {
            all_info = false;
            members.push_back(current_member);
            current_member.m_name = "";
        }
    }

    std::sort(members.begin(), members.end());
    for(auto& member : members)
    {
        ofstr << member.m_member_code << "," << member.m_name << "," 
        << member.m_street_num << " " <<  member.m_street_name << 
        "," << member.m_phoneNum << "," << member.Mbr_Amt[0] << ","
        << member.Mbr_Amt[1] << "," << member.Mbr_Amt[2] << ","
        << member.Mbr_Amt[3] << "," << member.Mbr_Amt[4] << "\n";
    }
    std::cout << "Successful wrote to " << outputFile << "\n";

    std::ofstream lessThan2024{"2024_less_2023_and_0.csv"};
    std::for_each(members.begin(), members.end(), [&lessThan2024](const Member& member)
    {
        if((std::stod(member.Mbr_Amt[1]) > std::stod(member.Mbr_Amt[2])) 
        || std::stod(member.Mbr_Amt[3]) == 0)
            addToFile(lessThan2024, member);
    }); 
}

int main()
{
    read_new_file_updated();
}